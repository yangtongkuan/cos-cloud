//package com.cos.cloud.user.service.user;
//
//
//public class ActionRole {
//
//	public interface Level {
//		static final String system = "system";
//		static final String acquirer = "acquirer";
//		static final String group = "group";
//		static final String company = "company";
//		static final String store = "store";
//	}
//
//	public interface Type {
//		public interface Acquirer {
//			static final String read = "acquier.read";
//			static final String write = "acquier.write";
//			static final String file = "acquier.file";
//		}
//		public interface Group {
//			static final String read = "group.read";
//			static final String write = "group.write";
//		}
//		public interface Company {
//			static final String read = "company.read";
//			static final String write = "company.write";
//			static final String paymentRead = "company.paymentRead";
//		}
//		public interface Store {
//			static final String read = "store.read";
//			static final String write = "store.write";
//		}
//		public interface User {
//			static final String read = "user.read";
//			static final String write = "user.write";
//		}
//		public interface Device {
//			static final String read = "device.read";
//			static final String write = "device.write";
//		}
//		public interface Transaction {
//			static final String read = "transaction.read";
//			static final String write = "transaction.write";
//		}
//	}
//
//	private static final byte SYSTEM = 1 << 0;
//	private static final byte ACQUIRE = 1 << 1;
//	private static final byte GROUP = 1 << 2;
//	private static final byte COMPANY = 1 << 3;
//	private static final byte STORE = 1 << 4;
//	private static final byte USER = 1 << 5;
//	private static final byte DEVICE = 1 << 6;
//	private static final byte TRANSACTION = (byte) (1 << 7);
//
//	public static void generateUserRoles(User user) {
//
//		if (user.getStore() != null) { // Level store
//			generateRoles(TRANSACTION, User.ROLE_MANAGER, user, true);
//			if (user.getRole() == User.ROLE_MANAGER) {
//				generateRoles(USER | DEVICE, User.ROLE_MANAGER, user, true);
//			}
//			generateRoles(STORE, User.ROLE_USER, user, true);
//			//generateRoles(COMPANY, User.ROLE_USER, user, false);
//		} else if (user.getCompany() != null) { // Level Company
//			generateRoles(TRANSACTION, User.ROLE_USER, user, true);
//			generateRoles(STORE | USER | DEVICE, user.getRole(), user, true);
//			generateRoles(COMPANY, User.ROLE_USER, user, true);
//		} else if (user.getGroup() != null) { // Level Group
//			generateRoles(TRANSACTION, User.ROLE_USER, user, true);
//			generateRoles(COMPANY | STORE | USER | DEVICE, user.getRole(), user, true);
//			generateRoles(GROUP, User.ROLE_USER, user, true);
//		} else if (user.getAcquirer() != null) { // Level Acquire
//			generateRoles(TRANSACTION, user.getRole(), user, true);
//			generateRoles(GROUP | COMPANY | STORE | USER | DEVICE, user.getRole(), user, true);
//			generateRoles(ACQUIRE, User.ROLE_USER, user, true);
//		} else { // Level System
//			generateRoles(TRANSACTION, user.getRole(), user, true);
//			generateRoles(SYSTEM | ACQUIRE | GROUP | COMPANY | STORE | USER | DEVICE, user.getRole(), user, true);
//		}
//	}
//
//	private static void generateRoles(int type, int role, User user, boolean isHasLevel) {
//
//		if ((type & SYSTEM) != 0) {
//			if (isHasLevel) user.addRoleLevel(Level.system);
//		}
//		if ((type & ACQUIRE) != 0) {
//			if (isHasLevel) user.addRoleLevel(Level.acquirer);
//			if (role == User.ROLE_USER) {
//				user.addRoleType(Type.Acquirer.read);
////				if (user.getAcquirer()!=null) {
////					user.addRoleType(Type.Acquirer.file);
////				}
//			} else if (role == User.ROLE_MANAGER) {
//				user.addRoleType(Type.Acquirer.read);
//				user.addRoleType(Type.Acquirer.write);
////				if (user.getAcquirer()!=null) {
////					user.addRoleType(Type.Acquirer.file);
////				}
//			}
//		}
//		if ((type & GROUP) != 0) {
//			if (isHasLevel) user.addRoleLevel(Level.group);
//			if (role == User.ROLE_USER) {
//				user.addRoleType(Type.Group.read);
//			} else if (role == User.ROLE_MANAGER) {
//				user.addRoleType(Type.Group.read);
//				user.addRoleType(Type.Group.write);
//			}
//		}
//		if ((type & COMPANY) != 0) {
//			if (isHasLevel) user.addRoleLevel(Level.company);
//			if (role == User.ROLE_USER) {
//				user.addRoleType(Type.Company.read);
//				if ((user.getAcquirer()!=null && user.getGroup()==null) || user.getAcquirer() == null
//						|| (user.getCompany()!=null && user.getRole() == User.ROLE_MANAGER)) {
//					user.addRoleType(Type.Company.paymentRead);
//				}
//			} else if (role == User.ROLE_MANAGER) {
//				user.addRoleType(Type.Company.read);
//				user.addRoleType(Type.Company.write);
//				user.addRoleType(Type.Company.paymentRead);
//			}
//		}
//		if ((type & STORE) != 0) {
//			if (isHasLevel) user.addRoleLevel(Level.store);
//			if (role == User.ROLE_USER) {
//				user.addRoleType(Type.Store.read);
//			} else if (role == User.ROLE_MANAGER) {
//				user.addRoleType(Type.Store.read);
//				user.addRoleType(Type.Store.write);
//			}
//		}
//		if ((type & USER) != 0) {
//			if (role == User.ROLE_USER) {
//				user.addRoleType(Type.User.read);
//			} else if (role == User.ROLE_MANAGER) {
//				user.addRoleType(Type.User.read);
//				user.addRoleType(Type.User.write);
//			}
//		}
//		if ((type & DEVICE) != 0) {
//			if (role == User.ROLE_USER) {
//				user.addRoleType(Type.Device.read);
//			} else if (role == User.ROLE_MANAGER) {
//				user.addRoleType(Type.Device.read);
//				user.addRoleType(Type.Device.write);
//			}
//		}
//		if ((type & TRANSACTION) != 0) {
//			if (role == User.ROLE_USER) {
//				user.addRoleType(Type.Transaction.read);
//			} else if (role == User.ROLE_MANAGER) {
//				user.addRoleType(Type.Transaction.read);
//				user.addRoleType(Type.Transaction.write);
//			}
//		}
//	}
//}
