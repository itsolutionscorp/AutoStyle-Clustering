class Say
  NUMBER_NAMES = { 0 => '_zero', 1 => 'one', 2 => 'two', 3 => 'three',
                   4 => 'four', 5 => 'five', 6 => 'six', 7 => 'seven',
                   8 => 'eight', 9 => 'nine', 10 => 'ten', 11 => 'eleven',
                   12 => 'twelve', 13 => 'thirteen', 14 => 'fourteen',
                   15 => 'fifteen', 16 => 'sixteen', 17 => 'seventeen',
                   18 => 'eighteen', 19 => 'nineteen', 20 => 'twenty',
                   30 => 'thirty', 40 => 'forty', 50 => 'fifty',
                   60 => 'sixty', 70 => 'seventy', 80 => 'eighty',
                   90 => 'ninety' }
  COMMAS       = %w(_hundred thousand million billion trillion)

  def initialize(num)
    @num = num
  end

  def in_english
    fail ArgumentError unless @num.between?(0, 999_999_999_999)
    return 'zero' if @num.to_i == 0
    c = find_commas(@num).map { |h, t| [hundreds(h), tens(t)] }
    d = name_commas(c).join(' ') # .join(', ')
    e = clean_placeholders(d)
    clean_whitespace(e)
  end

  private

  def find_commas(n)
    n.to_s.chars.reverse.each_slice(3).to_a.reverse.map { |x| x.reverse }
     .map { |s| s.unshift('0') until s.size == 3; s }
     .map { |a| [a[0], [a[1], a[2]].join] }
  end

  def hundreds(h)
    "#{NUMBER_NAMES[h.to_i]} hundred"
  end

  def tens(n)
    if n.to_i == 0
      ''
    elsif n.to_i <= 20
      NUMBER_NAMES[n.to_i]
    else
      prefix = NUMBER_NAMES[n[0].to_i * 10]
      suffix = NUMBER_NAMES[n[1].to_i]
      "#{prefix}-#{suffix}".sub('-_zero', '')
    end
  end

  def name_commas(c)
    c.reverse.zip(COMMAS).reverse
     .map { |s| s.join(' ') }
  end

  def clean_placeholders(str)
    str.gsub(/_zero hundred(\s+(thousand|million))*/, '')
       .gsub(/_hundred/, '')
       .strip.sub('_', '')
  end

  def clean_whitespace(str)
    str.gsub(/\s+/, ' ').strip
  end
end
