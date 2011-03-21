/**
 * Copyright 25.2.11 (c) DataLite, spol. s r.o. All rights reserved.
 * Web: http://www.datalite.cz    Mail: info@datalite.cz
 */
package cz.datalite.zk.liferay.mock;


import com.liferay.counter.service.CounterLocalService;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Digester;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.model.*;
import com.liferay.portal.model.impl.*;
import com.liferay.portal.service.*;
import com.liferay.portal.util.Portal;
import com.liferay.portal.util.PortalUtil;

import java.util.Arrays;
import java.util.Random;

import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class PortalMockFactory
{
    protected CompanyMockFactory companyMockFactory;
    protected UserMockFactory userMockFactory;

    /**
     * Construc Portal Mock Factory with company and user mocks.
     *
     * @param companyMockFactory
     * @param userMockFactory
     */
    public PortalMockFactory(CompanyMockFactory companyMockFactory, UserMockFactory userMockFactory)
    {
        this.companyMockFactory = companyMockFactory;
        this.userMockFactory = userMockFactory;
    }

    /**
     * Initialize all mockes for Liferay.
     *
     * If you need refresh basic mock configuration, just call this method - it sets all services
     * with a fresh instance and then it initializes basic values.
     */
    public void initLiferay()
    {
        try {
            // classloader is the web app
            PortalClassLoaderUtil.setClassLoader(PortalMockFactory.class.getClassLoader());

            mockServices();
            mockDynamicQuery();
            fillTestData();

        } catch (PortalException ex) {
            throw new RuntimeException(ex);
        } catch (SystemException ex) {
            throw new RuntimeException(ex);
        }
    }


    /**
     * Normaly Liferay regsiters service in utility class via Spring.
     *
     * For each utility class, we create a mock service with RETURNS_MOCKS (create not null objects for all functions).
     * We try to avoid as much NPE as possible.
     *
     * When some service call needs to be implemented, just add something like:
     *   when(CompanyLocalServiceUtil.getService().getCompany(companyId)).thenReturn(company);
     */
    public void mockServices() throws SystemException, PortalException {
        new AccountLocalServiceUtil().setService(mock(AccountLocalService.class, RETURNS_MOCKS));
        new AddressLocalServiceUtil().setService(mock(AddressLocalService.class, RETURNS_MOCKS));
        new BrowserTrackerLocalServiceUtil().setService(mock(BrowserTrackerLocalService.class, RETURNS_MOCKS));
        new ClusterGroupLocalServiceUtil().setService(mock(ClusterGroupLocalService.class, RETURNS_MOCKS));
        new CompanyLocalServiceUtil().setService(mock(CompanyLocalService.class, RETURNS_MOCKS));

        new ContactLocalServiceUtil().setService(mock(ContactLocalService.class, RETURNS_MOCKS));
        when(ContactLocalServiceUtil.getService().createContact(anyLong())).thenReturn(new ContactImpl());

        new CountryServiceUtil().setService(mock(CountryService.class, RETURNS_MOCKS));

        new EmailAddressLocalServiceUtil().setService(mock(EmailAddressLocalService.class, RETURNS_MOCKS));
        when(EmailAddressLocalServiceUtil.getService().createEmailAddress(anyLong())).thenReturn(new EmailAddressImpl());

        new GroupLocalServiceUtil().setService(mock(GroupLocalService.class, RETURNS_MOCKS));
        when(GroupLocalServiceUtil.getService().createGroup(anyLong())).thenReturn(new GroupImpl());

        new ImageLocalServiceUtil().setService(mock(ImageLocalService.class, RETURNS_MOCKS));
        new LayoutLocalServiceUtil().setService(mock(LayoutLocalService.class, RETURNS_MOCKS));
        new LayoutPrototypeLocalServiceUtil().setService(mock(LayoutPrototypeLocalService.class, RETURNS_MOCKS));
        new LayoutSetLocalServiceUtil().setService(mock(LayoutSetLocalService.class, RETURNS_MOCKS));
        new LayoutSetPrototypeLocalServiceUtil().setService(mock(LayoutSetPrototypeLocalService.class, RETURNS_MOCKS));
        new LayoutTemplateLocalServiceUtil().setService(mock(LayoutTemplateLocalService.class, RETURNS_MOCKS));
        new LockLocalServiceUtil().setService(mock(LockLocalService.class, RETURNS_MOCKS));
        new MembershipRequestLocalServiceUtil().setService(mock(MembershipRequestLocalService.class, RETURNS_MOCKS));
        new OrgLaborLocalServiceUtil().setService(mock(OrgLaborLocalService.class, RETURNS_MOCKS));
        new OrganizationLocalServiceUtil().setService(mock(OrganizationLocalService.class, RETURNS_MOCKS));
        new PasswordPolicyLocalServiceUtil().setService(mock(PasswordPolicyLocalService.class, RETURNS_MOCKS));
        new PasswordPolicyRelLocalServiceUtil().setService(mock(PasswordPolicyRelLocalService.class, RETURNS_MOCKS));
        new PasswordTrackerLocalServiceUtil().setService(mock(PasswordTrackerLocalService.class, RETURNS_MOCKS));
        new PermissionLocalServiceUtil().setService(mock(PermissionLocalService.class, RETURNS_MOCKS));

        new PhoneLocalServiceUtil().setService(mock(PhoneLocalService.class, RETURNS_MOCKS));
        when(PhoneLocalServiceUtil.getService().createPhone(anyLong())).thenReturn(new PhoneImpl());

        new PluginSettingLocalServiceUtil().setService(mock(PluginSettingLocalService.class, RETURNS_MOCKS));
        new PortalLocalServiceUtil().setService(mock(PortalLocalService.class, RETURNS_MOCKS));
        new PortletItemLocalServiceUtil().setService(mock(PortletItemLocalService.class, RETURNS_MOCKS));
        new PortletPreferencesLocalServiceUtil().setService(mock(PortletPreferencesLocalService.class, RETURNS_MOCKS));
        new QuartzLocalServiceUtil().setService(mock(QuartzLocalService.class, RETURNS_MOCKS));
        new ReleaseLocalServiceUtil().setService(mock(ReleaseLocalService.class, RETURNS_MOCKS));
        new ResourceActionLocalServiceUtil().setService(mock(ResourceActionLocalService.class, RETURNS_MOCKS));
        new ResourceCodeLocalServiceUtil().setService(mock(ResourceCodeLocalService.class, RETURNS_MOCKS));
        new ResourcePermissionLocalServiceUtil().setService(mock(ResourcePermissionLocalService.class, RETURNS_MOCKS));

        new RoleLocalServiceUtil().setService(mock(RoleLocalService.class, RETURNS_MOCKS));
        when(RoleLocalServiceUtil.getService().hasUserRole(anyLong(), anyLong(), anyString(), anyBoolean())).thenReturn(true);

        new ServiceComponentLocalServiceUtil().setService(mock(ServiceComponentLocalService.class, RETURNS_MOCKS));
        new ShardLocalServiceUtil().setService(mock(ShardLocalService.class, RETURNS_MOCKS));
        new SubscriptionLocalServiceUtil().setService(mock(SubscriptionLocalService.class, RETURNS_MOCKS));
        new TeamLocalServiceUtil().setService(mock(TeamLocalService.class, RETURNS_MOCKS));
        new ThemeLocalServiceUtil().setService(mock(ThemeLocalService.class, RETURNS_MOCKS));
        new TicketLocalServiceUtil().setService(mock(TicketLocalService.class, RETURNS_MOCKS));
        new UserGroupGroupRoleLocalServiceUtil().setService(mock(UserGroupGroupRoleLocalService.class, RETURNS_MOCKS));
        new UserGroupLocalServiceUtil().setService(mock(UserGroupLocalService.class, RETURNS_MOCKS));
        new UserGroupRoleLocalServiceUtil().setService(mock(UserGroupRoleLocalService.class, RETURNS_MOCKS));
        new UserIdMapperLocalServiceUtil().setService(mock(UserIdMapperLocalService.class, RETURNS_MOCKS));
        new UserLocalServiceUtil().setService(mock(UserLocalService.class, RETURNS_MOCKS));
        new UserTrackerLocalServiceUtil().setService(mock(UserTrackerLocalService.class, RETURNS_MOCKS));
        new UserTrackerPathLocalServiceUtil().setService(mock(UserTrackerPathLocalService.class, RETURNS_MOCKS));
        new WebDAVPropsLocalServiceUtil().setService(mock(WebDAVPropsLocalService.class, RETURNS_MOCKS));
        new WebsiteLocalServiceUtil().setService(mock(WebsiteLocalService.class, RETURNS_MOCKS));
        new WorkflowDefinitionLinkLocalServiceUtil().setService(mock(WorkflowDefinitionLinkLocalService.class, RETURNS_MOCKS));
        new WorkflowInstanceLinkLocalServiceUtil().setService(mock(WorkflowInstanceLinkLocalService.class, RETURNS_MOCKS));

        new ListTypeServiceUtil().setService(mock(ListTypeService.class, RETURNS_MOCKS));
        when(ListTypeServiceUtil.getService().getListTypes(anyString())).thenReturn(Arrays.asList((ListType) new ListTypeImpl()));

        new PortalUtil().setPortal(mock(Portal.class));
        new DigesterUtil().setDigester(mock(Digester.class));

        new CounterLocalServiceUtil().setService(mock(CounterLocalService.class, RETURNS_MOCKS));
        when(CounterLocalServiceUtil.getService().increment(anyString())).thenReturn(new Random().nextLong());

        new PortletLocalServiceUtil().setService(mock(PortletLocalService.class, RETURNS_MOCKS));

        companyMockFactory.setupClassNames();
    }

    /**
     * Setup basic classes for DynamicQuery Liferay API with dummy implementation.
     *
     * We will not actually support dynamic queries - the only implementation is done by RETURNS_MOCKS to avoid NPE.
     * What we need is to avoid method in constructing queries. This method contains mockup of:
     * <ul>
     *   <li>PortalClassLoaderUtil - dynamic class loader (use classloader of this class instead)</li>
     *   <li>DynamicQueryFactoryUtil - return dummy implementation of DynamicQueryFactory</li>
     *   <li>RestrictionsFactoryUtil - RestrictionsFactory with dummy implementation</li>
     *   <li>ProjectionFactoryUtil - ProjectionFactory with dummy implementation</li>
     * </ul>
     */
    public void mockDynamicQuery()
    {
        new DynamicQueryFactoryUtil().setDynamicQueryFactory(mock(DynamicQueryFactory.class, RETURNS_MOCKS));
        new RestrictionsFactoryUtil().setRestrictionsFactory(mock(RestrictionsFactory.class, RETURNS_MOCKS));
        new ProjectionFactoryUtil().setProjectionFactory(mock(ProjectionFactory.class, RETURNS_MOCKS));
        new OrderFactoryUtil().setOrderFactory(mock(OrderFactory.class, RETURNS_MOCKS));
    }

    private void fillTestData() throws PortalException, SystemException
    {
        when(PortletLocalServiceUtil.getPortletById(anyString())).thenReturn(new PortletImpl());

        // default and the only one company (portal instance)
        companyMockFactory.createCompanyImpl("company", CompanyMockFactory.DEFAULT_COMPANY_ID);

        // main group
        Group group = companyMockFactory.createGroupImpl("group", 1);

        // with serveral organizations
        Organization orgDefault = companyMockFactory.createOrganizationImpl("organization", 1);
        companyMockFactory.addOrganizationToGroup(orgDefault, group);

        Organization orgDatalite = companyMockFactory.createOrganizationImpl("datalite", 1);
        companyMockFactory.addOrganizationToGroup(orgDatalite, group);

        // and several users
        User admin = userMockFactory.createUserImpl("admin", 1);
        Contact adminContact = userMockFactory.createContactImpl("admin", 1);
        Address adminAddress = userMockFactory.createAddressImpl("admin", 1);
        userMockFactory.addContactToUser(adminContact, admin);
        userMockFactory.addAddressToContact(adminAddress, adminContact);

        User edudant = userMockFactory.createUserImpl("edudant", 2);
        Contact edudantContact = userMockFactory.createContactImpl("edudant", 2);
        Address edudantAddress = userMockFactory.createAddressImpl("edudant", 2);
        userMockFactory.addContactToUser(edudantContact, edudant);
        userMockFactory.addAddressToContact(edudantAddress, edudantContact);

        User francimor = userMockFactory.createUserImpl("francimor", 3);
        Contact francimorContact = userMockFactory.createContactImpl("francimor", 3);
        Address francimorAddress = userMockFactory.createAddressImpl("francimor", 3);
        userMockFactory.addContactToUser(francimorContact, francimor);
        userMockFactory.addAddressToContact(francimorAddress, francimorContact);

        userMockFactory.createUserQuery( Arrays.asList(admin, edudant, francimor) );

    }

}