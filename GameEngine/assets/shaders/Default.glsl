//#type vertex
#version 330 core
layout (location=0) in vec3 aPos;
layout (location=1) in vec4 aColor;
layout (location=2) in vec2 aTexCords;

uniform mat4 projection;
uniform mat4 view;


out vec4 fColor;
out vec2 fTexCords;

void main(){

    fColor=aColor;
    fTexCords=aTexCords;

    gl_Position=projection*view * vec4(aPos,1.0);
}

//#type fragment
#version 330 core

uniform float uTime;
uniform sampler2D TEX_SAMPLER;

out vec4 color;
in vec4 fColor;
in vec2 fTexCords;
void main(){

    float noise = fract(sin(dot(fColor.xy,vec2(12.9898,78.233)))*43758.5453);
    color= texture(TEX_SAMPLER,fTexCords)*noise;
}