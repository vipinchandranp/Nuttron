
package com.cooltrickshome;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "${name}",
    "${type}",
    "${value}"
})
public class $Prop {

    @JsonProperty("${name}")
    private String $Name;
    @JsonProperty("${type}")
    private String $Type;
    @JsonProperty("${value}")
    private String $Value;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("${name}")
    public String get$Name() {
        return $Name;
    }

    @JsonProperty("${name}")
    public void set$Name(String $Name) {
        this.$Name = $Name;
    }

    public $Prop with$Name(String $Name) {
        this.$Name = $Name;
        return this;
    }

    @JsonProperty("${type}")
    public String get$Type() {
        return $Type;
    }

    @JsonProperty("${type}")
    public void set$Type(String $Type) {
        this.$Type = $Type;
    }

    public $Prop with$Type(String $Type) {
        this.$Type = $Type;
        return this;
    }

    @JsonProperty("${value}")
    public String get$Value() {
        return $Value;
    }

    @JsonProperty("${value}")
    public void set$Value(String $Value) {
        this.$Value = $Value;
    }

    public $Prop with$Value(String $Value) {
        this.$Value = $Value;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public $Prop withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append($Name).append($Type).append($Value).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof $Prop) == false) {
            return false;
        }
        $Prop rhs = (($Prop) other);
        return new EqualsBuilder().append($Name, rhs.$Name).append($Type, rhs.$Type).append($Value, rhs.$Value).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
