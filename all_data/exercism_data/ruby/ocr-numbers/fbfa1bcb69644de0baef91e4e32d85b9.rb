class OCR
  FONT = { ' _ | ||_|' => '0',
           '     |  |' => '1',
           ' _  _||_ ' => '2',
           ' _  _| _|' => '3',
           '   |_|  |' => '4',
           ' _ |_  _|' => '5',
           ' _ |_ |_|' => '6',
           ' _   |  |' => '7',
           ' _ |_||_|' => '8',
           ' _ |_| _|' => '9' }

  attr_reader :text, :lines

  def initialize text
    @text = text
    @lines = text.split("\n").delete_if { |e| e == '' }
  end

  def convert
    t = triple_line.map do |line|
      sl1, sl2, sl3 = sub_lines line
      sl1.zip(sl2, sl3).map(&:join).map { |c| FONT[c] || '?' }.join
    end
    t.join ','
  end

  private

  def triple_line
    lines.each_slice 3
  end

  def sub_lines line
    parts = line.map { |l| l.chars.each_slice(3).map(&:join) }
    parts.unshift(['   ']) if parts.size == 2
    parts.each { |p| p.map! { |t| t.ljust 3, ' ' } }
    parts
  end
end
