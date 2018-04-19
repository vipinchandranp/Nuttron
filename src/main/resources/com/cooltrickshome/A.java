
package com.cooltrickshome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    "${tablename}",
    "${props}"
})
public class A {

    @JsonProperty("${tablename}")
    private String $Tablename;
    @JsonProperty("${props}")
    private List<com.cooltrickshome.$Props> $Props = new ArrayList<com.cooltrickshome.$Props>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("${tablename}")
    public String get$Tablename() {
        return $Tablename;
    }

    @JsonProperty("${tablename}")
    public void set$Tablename(String $Tablename) {
        this.$Tablename = $Tablename;
    }

    public A with$Tablename(String $Tablename) {
        this.$Tablename = $Tablename;
        return this;
    }

    @JsonProperty("${props}")
    public List<com.cooltrickshome.$Props> get$Props() {
        return $Props;
    }

    @JsonProperty("${props}")
    public void set$Props(List<com.cooltrickshome.$Props> $Props) {
        this.$Props = $Props;
    }

    public A with$Props(List<com.cooltrickshome.$Props> $Props) {
        this.$Props = $Props;
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

    public A withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append($Tablename).append($Props).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof A) == false) {
            return false;
        }
        A rhs = ((A) other);
        return new EqualsBuilder().append($Tablename, rhs.$Tablename).append($Props, rhs.$Props).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
