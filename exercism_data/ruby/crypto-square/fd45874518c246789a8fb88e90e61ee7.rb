class Array
  def safe_transpose
    result = []
    max_size = self.max { |a,b| a.size <=> b.size }.size
    max_size.times do |i|
      result[i] = Array.new(self.first.size)
      self.each_with_index { |r,j| result[i][j] = r[i] }
    end
    result
  end
end

class Crypto

  def initialize str
    @str = str
  end

  def normalize_plaintext
    @str.downcase.gsub(/\W/, "")
  end

  def size
    (0..normalize_plaintext.size).detect do |i| 
      i**2 >= normalize_plaintext.size 
    end
  end

  def plaintext_segments
    normalize_plaintext.chars
                       .each_slice(size)
                       .inject([]) { |res, chr| res << chr.join }
  end

  def ciphertext
    plaintext_segments.safe_transpose.map {|chr| chr.compact.join }.join ""
  end

  def normalize_ciphertext
    ciphertext.chars.each_slice(5)
                    .inject([]) { |res, chr| res << chr.join }.join " " 
  end

end
