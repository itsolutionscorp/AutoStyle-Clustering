class Crypto
  def initialize text
    @text = text
  end

  def normalize_plaintext
    @text.gsub(/\W/, "").downcase
  end

  def size
    Math.sqrt(normalize_plaintext.length).ceil
  end

  def plaintext_segments
    normalize_plaintext.scan(/.{1,#{size}}/)
  end

  def ciphertext
    cipher = ""
    i = 0
    segments = plaintext_segments.map { |i| i.chars }
    while i < self.size
      segments.each do |segment|
        next if segment[i].nil?
        cipher << segment[i]
      end
      i += 1
    end
    cipher
  end

  def normalize_ciphertext
    ciphertext.scan(/.{1,5}/).join(" ")
  end
end
