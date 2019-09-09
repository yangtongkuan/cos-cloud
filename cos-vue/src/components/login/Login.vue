<template>
  <div class="dashboard">

    <div class="login">

      <div class="titles">
        <span>{{AppName}}</span>
      </div>
      <div class="tab_changes">
        <div class="left" @click="changeLoginWay('1')" :class="{active:(activeIndex === '1')}">{{userLogin}}</div>
        <div class="right" @click="changeLoginWay('2')" :class="{active:(activeIndex === '2')}">{{phoneLogin}}</div>
      </div>
      <div>
        <el-form v-if="activeIndex === '1'" :model="userLoginForm" ref="userLoginForm" :rules="userLoginRules" label-width="210px">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="userLoginForm.username" style="width: 200px;">
            </el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="userLoginForm.password" style="width: 200px;"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')" @keyup.enter.native="submitForm('ruleForm')"
                       style="width: 200px;background-color:#00978A;color:white;">登录
            </el-button>
          </el-form-item>
        </el-form>
        <el-form v-else :model="phoneLoginForm" ref="phoneLoginForm" :rules="phoneLoginRules" label-width="210px">
          <el-form-item label="用户名" prop="phone">
            <el-input v-model="phoneLoginForm.phone" style="width: 200px;">
            </el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password2">
            <el-input type="password" v-model="phoneLoginForm.password" style="width: 200px;"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')" @keyup.enter.native="submitForm('ruleForm')"
                       style="width: 200px;background-color:#00978A;color:white;">登录
            </el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
  import storage from '../../assets/js/storage'
  import state from '../../assets/js/state'
  import {getLoginUrl} from '../main/config'

  export default {
    // Enter键登录
    created() {
      var _self = this;
      document.onkeydown = function (e) {
        if (window.event == undefined) {
          var key = e.keyCode;
        } else {
          var key = window.event.keyCode;
        }
        if (key == 13) {
          _self.submitForm('ruleForm')
        }
      };
    },
    data() {
      return {
        AppName: 'cos后台管理系统',
        activeIndex: '1',
        phoneLogin:'手机快速登录',
        userLogin:'账号密码登录',
        userLoginForm: {
          username: '',
          password: ''
        },
        userLoginRules: {
          username: [
            {required: true, message: '请输入用户名', trigger: 'blur'},
            {min: 4, max: 16, message: '长度在 4 到 16 个字符', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'},
            {min: 4, max: 16, message: '长度在 4 到 12 个字符', trigger: 'blur'}
          ],
        },
        phoneLoginForm: {
          phone: '',
          password: ''
        },
        phoneLoginRules: {
          phone: [
            {required: true, message: '请输入用户名', trigger: 'blur'},
            {min: 4, max: 12, message: '长度在 4 到 12 个字符', trigger: 'blur'}
          ],
          password2: [
            {required: true, message: '请输入密码', trigger: 'blur'},
            {min: 4, max: 12, message: '长度在 4 到 12 个字符', trigger: 'blur'}
          ],
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$router.push({path: "/Home/"});
          } else {
            this.$message.error('登录失败');
            return false;
          }
        });
      },
      changeLoginWay(index) {
       this.activeIndex = index
        if (index === '1') {
          this.phoneLoginForm.phone = ''
          this.phoneLoginForm.password = ''
        } else {
          this.userLoginForm.phone = ''
          this.userLoginForm.password = ''
        }
      }
    }
  }
</script>
<style scoped lang="scss">
  .dashboard {
    position: relative;
    width: 100%;
    height: 100%;
    margin: 0px;
    padding: 5% 0px;
    background: url('../../assets/icon/icon-background.jpg');
    background-size: 100% 100%;
  }

  .login {
    position: relative;
    width: 30%;
    height: 50%;
    /*margin-top: 7%;*/
    margin: 5% auto;
    border-radius: 3%;
    padding-bottom: 10px;
    background-color: white;
    border: 1px solid #E6E6FA;
  }

  .login .el-form-item__content {
    width: 300px;
  }
  .login .el-button {
    width: 300px;
  }
  .login .el-form {
    margin: 30px 80px auto 80px;
  }
  .error {
    display: block;
    text-align: center;
    color: red;
  }

  .titles {
    color: white;
    font-size: 35px;
    line-height: 80px;
    text-align: center;
    border-radius:6%;
    background-color: steelblue;
  }

  .tab_changes {
    color: #a6a6a6;
    font-size: 18px;
    line-height: 60px;
    text-align: center;
    margin-bottom: 30px;
    /*border-radius: 8%;*/
    background-color: white;
    padding: 0 10%;
  }

  .tab_changes .left {
    float: left;
    display: inline-block;
    width: 50%;
    font-size: 18px;
    text-align: center;
    /*border-bottom-width: 5px;*/
    /*border-bottom-style: solid;*/
    border-bottom: 3px solid;
    cursor: pointer;
  }

  .tab_changes .right {
    display: inline-block;
    width: 50%;
    font-size: 18px;
    text-align: center;
    /*border-bottom-width: 5px;*/
    /*border-bottom-style: solid;*/
    border-bottom: 3px solid;
    cursor: pointer;
  }

  .active {
    color:  #307733;
    border-bottom-color: #307733;
  }

</style>
