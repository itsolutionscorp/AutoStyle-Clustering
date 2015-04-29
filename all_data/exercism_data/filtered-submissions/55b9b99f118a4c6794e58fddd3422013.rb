def compute alpha, beta

    zeta = alpha.length < beta.length ? alpha : beta


    diff = 0.upto(zeta.length - 1).map do |index|
      alpha[index] == beta[index] ? 0 : 1
    end


    diff.inject(0, :+)
  end