/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.3.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package my.tiran.user.controller;

import my.tiran.user.model.dto.DefaultError;
import my.tiran.user.model.dto.RegisterUser;
import my.tiran.user.model.dto.RequestAuthentication;
import my.tiran.user.model.dto.ResponseAuthentication;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
@Validated
@Controller
@Tag(name = "Authentication", description = "the Authentication API")
public interface AuthApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /auth/authentication : User Authentication
     *
     * @param requestAuthentication  (optional)
     * @return Successful (status code 200)
     *         or  (status code 200)
     */
    @Operation(
        operationId = "userAuthentication",
        summary = "User Authentication",
        tags = { "Authentication" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseAuthentication.class))
            }),
            @ApiResponse(responseCode = "default", description = "", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = DefaultError.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/auth/authentication",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    default ResponseEntity<ResponseAuthentication> userAuthentication(
        @Parameter(name = "RequestAuthentication", description = "") @Valid @RequestBody(required = false) RequestAuthentication requestAuthentication
    ) throws Exception {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"accessToken\" : \"accessToken\", \"refreshToken\" : \"refreshToken\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /auth/register : User Register
     *
     * @param registerUser  (optional)
     * @return Successful (status code 200)
     *         or  (status code 200)
     */
    @Operation(
        operationId = "userRegister",
        summary = "User Register",
        tags = { "Authentication" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseAuthentication.class))
            }),
            @ApiResponse(responseCode = "default", description = "", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = DefaultError.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/auth/register",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    default ResponseEntity<ResponseAuthentication> userRegister(
        @Parameter(name = "RegisterUser", description = "") @Valid @RequestBody(required = false) RegisterUser registerUser
    ) throws Exception {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"accessToken\" : \"accessToken\", \"refreshToken\" : \"refreshToken\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
