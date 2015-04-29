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
    chunk_data(cipher.chars, cipher_segment_size, ' ').join.strip
  end

  private

  def cipher_segment_size
    size > 2 ? size - 1 : size
  end

  def segment_size
    Math.sqrt(normalize_plaintext.length).ceil
  end

  def segments
    chunk_data(normalize_plaintext.chars, size).delete_if(&:empty?)
  end

  def chunk_data(data, chunk_size, padding = '')
    size.times.each_with_object([]) do |_i, result|
      result << "#{data.shift(chunk_size).join}#{padding}"
    end
  end

  def cipher
    size.times.each_with_object('') do |times, result|
      plaintext_segments.each { |row| result << row[times] if row[times] }
    end
  end
end
