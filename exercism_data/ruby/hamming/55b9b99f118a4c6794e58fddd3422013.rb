class Hamming
  def self.compute alpha, beta
    # let's find the shorter string of the two given strings
    zeta = alpha.length < beta.length ? alpha : beta

    # compute an array with the diff bits
    diff = 0.upto(zeta.length - 1).map do |index|
      alpha[index] == beta[index] ? 0 : 1
    end

    # inject with summation to add the difference bits, which is our answer :)
    diff.inject(0, :+)
  end
end
