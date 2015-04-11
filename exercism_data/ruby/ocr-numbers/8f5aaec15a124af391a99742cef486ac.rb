class OCR
  FONT = { ' _ | ||_|'   => '0', '     |  |'   => '1', ' _  _||_ '   => '2',
           ' _  _| _|'   => '3', '   |_|  |'   => '4', ' _ |_  _|'   => '5',
           ' _ |_ |_|'   => '6', ' _   |  |'   => '7', ' _ |_||_|'   => '8',
           ' _ |_| _|'   => '9' }

  attr_reader :text

  def initialize text
    @text = text
  end

  def convert
    prep = text.split("\n").delete_if { |e| e == '' }
    prep.each_slice(3).with_object([]) do |line, a|
      sub_lines = line.map { |l| l.chars.each_slice(3).to_a.map { |t| t.join } }
      sub_lines.unshift(['   ']) if sub_lines.size == 2
      sub_lines.each { |sl| sl.map! { |t| t.ljust(3, ' ') } }
      sl1, sl2, sl3 = sub_lines
      a << sl1.zip(sl2, sl3).map { |c| c.join }.map { |c| FONT[c] || '?' }.join
    end.join(',')
  end
end
