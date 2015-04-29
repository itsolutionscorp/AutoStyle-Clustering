class Phone
  attr_reader :area_code, :exchange, :subscriber

  def initialize(input)
    @area_code, @exchange, @subscriber = extract digits input
  end

  def number
    area_code + exchange + subscriber
  end

  def to_s
    "(#{ area_code }) #{ exchange }-#{ subscriber }"
  end

  private

  def extract(string)
    string =~ /^1?(\d{3})(\d{3})(\d{4})$/ ? [$1, $2, $3] : extract("0"*10)
  end

  def digits(string)
    string.gsub(/\D/, '')
  end
end
