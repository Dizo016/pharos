package br.com.pharos.domain.user;

public class UserMapper {

    public static User toEntity(UserRequest request, String encodedPassword) {
        return User.builder()
                .name(request.name())
                .login(request.login())
                .password(encodedPassword)
                .userRole(request.userRole())
                .build();
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getLogin(),
                user.getUserRole(),
                user.getActive()
        );
    }
}