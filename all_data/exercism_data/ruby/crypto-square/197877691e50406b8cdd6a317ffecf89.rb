class Crypto

  def initialize(raw_text)
    @raw_text = raw_text
  end

  def normalize_plaintext
    @normalize_plaintext ||= normalize
  end

  def size
    @size ||= Math.sqrt(normalize_plaintext.chars.length).ceil
  end

  def plaintext_segments
    @plaintext_segments ||= build_plaintext_segments
  end

  def ciphertext
    @ciphertext ||= cipher.join
  end

  def normalize_ciphertext
    ciphertext.chars.each_slice(5).map { |chars| chars.join }.join(" ")
  end

  private

  def normalize
    @raw_text.gsub(/\W/, "").downcase
  end

  def build_plaintext_segments
    normalize_plaintext.chars.each_slice(size).map { |chars| chars.join }
  end

  def cipher
    [].tap do |out|
      (0 .. size - 1).each do |x|
        s = ""
        (0 .. plaintext_segments.length - 1).each do |y|
          s << plaintext_segments[y][x] unless plaintext_segments[y][x].nil?
        end
        out << s
      end
    end
  end
end
