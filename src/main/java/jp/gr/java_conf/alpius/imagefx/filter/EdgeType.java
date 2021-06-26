package jp.gr.java_conf.alpius.imagefx.filter;

import java.awt.image.ConvolveOp;

public enum EdgeType {
    NO_OP, ZERO_FILL;

    int getAWTConstant() {
        switch (this) {
            case NO_OP: return ConvolveOp.EDGE_NO_OP;
            case ZERO_FILL: return ConvolveOp.EDGE_ZERO_FILL;
        }
        throw new AssertionError();
    }
}
