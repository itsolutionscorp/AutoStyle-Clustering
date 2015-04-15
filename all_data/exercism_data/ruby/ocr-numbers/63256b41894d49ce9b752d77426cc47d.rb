class OCR
  attr_reader :input

  HASH_MAP = {
    84 => '0',
    36 => '1',
    66 => '2',
    78 => '3',
    40 => '4',
    70 => '5',
    76 => '6',
    68 => '7',
    88 => '8',
    82 => '9'
  }

  CHAR_VALUES = {
    ' ' => 1,
    '_' => 2,
    '|' => 3
  }

  def initialize(inpu)
    @input = inpu
  end

  def convert
    each_row.reduce([]) do |acc, row|
      acc << each_character(row).reduce('') do |num, char|
        number = HASH_MAP.fetch(hash_number(char.transpose.map(&:join).reject { |s| s.strip.empty? }), '?')
        if number == '3'
          number = '?' unless char[1][1] == '_'
        end
        num + number
      end
    end.join(',')
  end

  def each_character(text)
    length = text.map(&:length).max
    text.map { |r| r = r.split(''); r << ' ' while r.length < length; r }.transpose.each_slice(3)
  end

  def hash_number(num)
    each_with_indexed_rows_columns(num).reduce(0) do |acc, (row, x)|
      acc + (x + 1) * row.reduce(0) do |acc2, (char, y)|
        acc2 + (y + 1) * CHAR_VALUES[char]
      end
    end
  end

  def each_row
    input.split("\n").each_slice(4)
  end

  def each_with_indexed_rows_columns(num)
    num.each_with_index.to_a.map do |s, index|
      [s.split('').each_with_index.to_a, index]
    end
  end
end
