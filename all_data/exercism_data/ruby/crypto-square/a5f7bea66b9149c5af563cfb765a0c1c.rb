class Crypto
  attr_reader :msg

  def initialize(msg)
    @msg = msg
  end

  def normalize_plaintext
    plaintext
  end

  def plaintext_segments
    rows
  end

  def size
    Math::sqrt(plaintext.length).ceil
  end

  def ciphertext
    columns.join
  end

  def normalize_ciphertext
    split_by_size(ciphertext, rows.size).join(' ')
  end

  private

  def plaintext
    @plaintext ||= msg.gsub(/\W/,'').downcase
  end

  def rows
    @rows ||= split_by_size(plaintext, size)
  end

  def columns
    @columns ||= Array.new(size) do |i|
      rows.inject('') { |acc, row| acc << (row[i] || '') }
    end
  end

  def split_by_size(string, size)
    string.scan(Regexp.new("\\w{1,#{size}}"))
  end
end
