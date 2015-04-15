# class for composing square code from message
class Crypto
  def initialize(message)
    @message = message
  end

  def normalize_plaintext
    @normalized ||= @message.gsub(/[^\w]/, '').downcase
  end

  def size
    @segment_size ||= segment_size
  end

  def plaintext_segments
    @segments ||= segments
  end

  def ciphertext
    @cipher ||= cipher
  end

  def normalize_ciphertext
    cipher_array = cipher.chars
    result = size.times.each_with_object('') do |_i, res|
      res << "#{cipher_array.shift(cipher_segment_size).join} "
    end
    result.strip
  end

  private

  def cipher_segment_size
    size > 2 ? size - 1 : size
  end

  def segment_size
    Math.sqrt(normalize_plaintext.length).ceil
  end

  def segments
    plaintext_array = normalize_plaintext.chars
    result = normalize_plaintext.chars.each_with_object([]) do |_i, res|
      res << plaintext_array.shift(size).join
    end
    result.delete_if(&:empty?)
  end

  def cipher
    plaintext_segments.first.size.times.reduce('') do |res, times|
      plaintext_segments.each { |row| res += row[times] if row[times] }
      res
    end
  end
end
