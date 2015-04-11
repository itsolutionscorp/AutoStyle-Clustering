class Hamming
  def self.compute(base, fingerprint)
    base, fingerprint = [base, fingerprint].map(&:each_byte)
    base.zip(fingerprint).reduce(0) do |sum, s|
      sum += (s[0] == s[1] || s[1].nil? ? 0 : 1)
    end
  end
end
