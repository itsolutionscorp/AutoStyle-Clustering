class Hamming
  def self.compute(first, second)
    first.scan(/./).zip(second.scan(/./)).select { |match| !match[0].nil? && !match[1].nil? && match[0] != match[1] }.length
  end
end
