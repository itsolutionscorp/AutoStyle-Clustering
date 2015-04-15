class Year
  class << self
    def leap?(year)
      div_4?(year) && !div_100?(year) || div_400?(year)
    end

    private

    [4,100,400].each { |mod|
      define_method "div_#{mod}?" do |year|
        (year % mod).zero?
      end
    }
  end
end
