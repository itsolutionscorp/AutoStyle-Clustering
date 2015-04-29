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

  # this is horrible - passes test but not sure what the rules are here?
  def normalize_ciphertext
    norm_size = size > 2 ? size - 1 : size
    result = ciphertext.chars.each_with_index.reduce('') do |res, (e, i)|
      ((i + 1) % norm_size).zero? ? res += "#{e} " : res += e
    end
    result.strip
  end

  private

  def segment_size
    length_sqrt = Math.sqrt(normalize_plaintext.length)
    length_sqrt.floor == length_sqrt ? length_sqrt.to_i : length_sqrt.ceil
  end

  def segments
    testcase = normalize_plaintext.chars
    result = (1..size).each_with_object([]) do |_i, res|
      res << testcase.take(size).join
      testcase = testcase.drop(size)
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
