class Bob

  def hey(input)
    heard = BlahBlahBlah.new(input)

    (express_shock     if heard.shouting?) ||
    (express_agreement if heard.question?) ||
    (express_anger     if heard.silence? ) ||
    express_boredom
  end

  private

  { :express_shock     => 'Woah, chill out!',
    :express_agreement => 'Sure.',
    :express_anger     => 'Fine. Be that way!',
    :express_boredom   => 'Whatever.'

  }.each_pair do |meth,value|
    define_method(meth) { value }
  end

  class BlahBlahBlah
    attr_reader :input

    def initialize(input)
      @input = input.to_s
    end

    def shouting?
      input.length > 0 && input.upcase == input
    end

    def question?
      input[-1,1] == '?'
    end

    def silence?
      input == ''
    end
  end

end
