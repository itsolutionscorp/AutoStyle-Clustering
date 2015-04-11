class Crypto
  attr_accessor :msg
  def initialize(msg)
    @msg = msg
    normalize_plaintext
  end

  def normalize_plaintext
    self.msg = self.msg.downcase.gsub(/\W/, "")
  end

  def size
    Math.sqrt(msg.size).ceil
  end

  def plaintext_segments
    results = []
    msg.split("").each_slice(size) { |s| results << s.join }
    results
  end

  def ciphertext
    words = plaintext_segments
    cipher = ""

    (0..words.length).each do |ctr|
      words.each { |word| cipher << word[ctr] if word[ctr] }
      ctr +=1
    end

    cipher
  end

  def normalize_ciphertext
    results = []
    ciphertext.split("").each_slice(5) { |s| results << s.join("") }
    results.join(" ")
  end

end
