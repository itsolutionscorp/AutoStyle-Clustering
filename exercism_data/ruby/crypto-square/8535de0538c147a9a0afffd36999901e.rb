class Crypto
  ACCEPTED_CHARACTERS = 'a-zA-Z0-9'

  def initialize(plaintext)
    @plaintext = plaintext
  end

  attr_reader :plaintext

  def normalize_plaintext
    @normalize_plaintext ||=
      plaintext.gsub(/[^#{ACCEPTED_CHARACTERS}]/, '').downcase
  end

  def size
    @size ||=
      Math.sqrt(normalize_plaintext.size).ceil
  end

  def plaintext_segments
    @plaintext_segments ||=
      segmentize_string normalize_plaintext, size
  end

  def ciphertext
    columns = Array.new(size) { "" }
    plaintext_segments.each do |row|
      row.chars.each_with_index do |char, column_num|
        columns[column_num] << char
      end
    end
    columns.join
  end

  def normalize_ciphertext
    segmentize_string(ciphertext, plaintext_segments.length).join ' '
  end

  private

  # Split a string into an array of segments of the same length
  # The last segment may be smaller than the others
  def segmentize_string(str, length)
    str.scan /.{1,#{length}}/
  end
end
