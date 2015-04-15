class Raindrops
  class Roof
    class << self
      private

      def special_case(name, number)
        define_method name.downcase do
          @o += name if @number % number == 0
          self
        end
      end
    end

    def initialize(number)
      @number = number
      @o      = ''
    end

    def convert
      pling
      plang
      plong
      to_s
    end

    def to_s
      (@o.empty? ? @number : @o).to_s
    end

    special_case("Pling", 3)
    special_case("Plang", 5)
    special_case("Plong", 7)
  end

  def self.convert(number)
    Roof.new(number).convert.to_s
  end
end
