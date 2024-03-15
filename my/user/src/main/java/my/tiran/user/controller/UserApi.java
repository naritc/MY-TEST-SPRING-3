/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.3.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package my.tiran.user.controller;

import my.tiran.user.model.dto.DefaultError;
import my.tiran.user.model.dto.DefaultSuccess;
import my.tiran.user.model.dto.GetMyUser;
import my.tiran.user.model.dto.MyUser;
import my.tiran.user.model.dto.MyUsers;
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
@Tag(name = "User Management", description = "the User Management API")
public interface UserApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * DELETE /user/disableMyUser : Disable MyUser
     *
     * @param myUser  (optional)
     * @return  (status code 200)
     *         or  (status code 200)
     */
    @Operation(
        operationId = "disableMyUser",
        summary = "Disable MyUser",
        tags = { "User Management" },
        responses = {
            @ApiResponse(responseCode = "200", description = "", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = DefaultSuccess.class))
            }),
            @ApiResponse(responseCode = "default", description = "", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = DefaultError.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/user/disableMyUser",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    default ResponseEntity<DefaultSuccess> disableMyUser(
        @Parameter(name = "MyUser", description = "") @Valid @RequestBody(required = false) MyUser myUser
    ) throws Exception {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PATCH /user/editMyUser : Edit MyUser
     *
     * @param myUser  (optional)
     * @return Successful (status code 200)
     *         or  (status code 200)
     */
    @Operation(
        operationId = "editMyUser",
        summary = "Edit MyUser",
        tags = { "User Management" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = MyUser.class))
            }),
            @ApiResponse(responseCode = "default", description = "", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = DefaultError.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PATCH,
        value = "/user/editMyUser",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    default ResponseEntity<MyUser> editMyUser(
        @Parameter(name = "MyUser", description = "") @Valid @RequestBody(required = false) MyUser myUser
    ) throws Exception {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"roles\" : [ \"roles\", \"roles\" ], \"email\" : \"email\", \"username\" : \"username\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /user/getMyUser : Get MyUser
     *
     * @param getMyUser  (optional)
     * @return Successful (status code 200)
     *         or  (status code 200)
     */
    @Operation(
        operationId = "getMyUser",
        summary = "Get MyUser",
        tags = { "User Management" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = MyUser.class))
            }),
            @ApiResponse(responseCode = "default", description = "", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = DefaultError.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/user/getMyUser",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    default ResponseEntity<MyUser> getMyUser(
        @Parameter(name = "GetMyUser", description = "") @Valid @RequestBody(required = false) GetMyUser getMyUser
    ) throws Exception {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"roles\" : [ \"roles\", \"roles\" ], \"email\" : \"email\", \"username\" : \"username\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /user/getMyUsers : Get MyUsers
     *
     * @return Successful (status code 200)
     *         or  (status code 200)
     */
    @Operation(
        operationId = "getMyUsers",
        summary = "Get MyUsers",
        tags = { "User Management" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = MyUsers.class))
            }),
            @ApiResponse(responseCode = "default", description = "", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = DefaultError.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/user/getMyUsers",
        produces = { "application/json" }
    )
    
    default ResponseEntity<MyUsers> getMyUsers(
        
    ) throws Exception {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"myUsers\" : [ { \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"roles\" : [ \"roles\", \"roles\" ], \"email\" : \"email\", \"username\" : \"username\" }, { \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"roles\" : [ \"roles\", \"roles\" ], \"email\" : \"email\", \"username\" : \"username\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
