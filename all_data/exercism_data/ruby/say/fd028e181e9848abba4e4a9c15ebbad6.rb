class Say
  def initialize(n)
    @n = n
    @lower = 0
    @upper = 1_000_000_000_000
    @small = [nil, 'one', 'two', 'three', 'four', 'five',
             'six', 'seven', 'eight', 'nine', 'ten',
             'eleven', 'twelve', 'thirteen', 'fourteen', 'fifteen',
             'sixteen', 'seventeen', 'eighteen', 'nineteen']
    @tens = [nil, nil, 'twenty', 'thirty', 'forty', 'fifty',
              'sixty', 'seventy', 'eighty', 'ninety']
    @big = [nil, 'thousand', 'million', 'billion']
  end

  def in_english
    raise ArgumentError unless (@lower...@upper).include?(@n)
    return 'zero' if @n == 0

    res = ""
    chunks.each_with_index do |c, i|
      next if c.zero?
      s = change(c)
      s << " " << @big[i] if @big[i]
      res.prepend(s << " ")
    end

    res.rstrip
  end

  private
  def chunks
    ch = []
    x = @n
    while x > 0
      ch << x % 1000
      x /= 1000
    end
    ch
  end

  def change(c)
    case c
    when 1...20
      res = @small[c].dup
    when 20...100
      res = @tens[c / 10].dup
      res << "-" << change(c % 10) unless (c % 10).zero?
    when 100...1000
      res = @small[c / 100].dup << " hundred"
      res << " " << change(c % 100) unless (c % 100).zero?
    end
    res
  end
end
