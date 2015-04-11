class Crypto
  attr_reader :size, :normalize_plaintext

  def initialize(message)
    @message = message
    normalize_message!
    set_size!
  end

  def plaintext_segments
    @normalize_plaintext
      .chars
      .each_slice(@size)
      .map { |segment_chars| segment_chars.join }
  end

  def ciphertext
    enumerable_segments = plaintext_segments
      .map { |text_segment| text_segment.chars }

    unless enumerable_segments.one?
      enumerable_segments
        .first
        .zip(*(enumerable_segments.drop(1).to_a))
        .map { |vertical_segment_chars| vertical_segment_chars.compact.join }
        .join
    else
      enumerable_segments.first.join
    end
  end

  def normalize_ciphertext
    ciphertext
      .chars
      .each_slice(5)
      .map { |segment_characters| segment_characters.join }
      .join ' '
  end

  private
  def normalize_message!
    @normalize_plaintext = @message.gsub(/[^a-zA-Z1-9]/, '').downcase
  end

  def set_size!
    @size = Math.sqrt(@normalize_plaintext.length).ceil
  end
end
