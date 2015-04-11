class OCR
  DIGITS = <<-DIGITS.chomp
 _     _  _     _  _  _  _  _
| |  | _| _||_||_ |_   ||_||_|
|_|  ||_  _|  | _||_|  ||_| _|

  DIGITS

  def initialize(text)
    @numbers = text.split("\n\n").map { |t| t.gsub(/\s+$/, '') }
  end

  def convert
    @numbers.map do |number|
      each_3px_block(number).map do |digit|
        each_3px_block(DIGITS).find_index(digit) || '?'
      end.join
    end.join(',')
  end

  private

  def each_3px_block(text = DIGITS)
    return to_enum(__method__, text) unless block_given?
    0.upto(text.each_line.map(&:length).max / 3 - 1) do |n|
      yield text.each_line.map { |line| line[n * 3, 3].rstrip }.join("\n")
    end
  end
end
