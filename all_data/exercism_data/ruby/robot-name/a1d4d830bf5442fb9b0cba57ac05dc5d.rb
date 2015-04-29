class Robot
  DIGITS = ('0'..'9').to_a
  LETTERS = ('A'..'Z').to_a
  PATTERN_MAP = { D: DIGITS, L: LETTERS }
  NAME_GENERATORS = %i(incrementing random)

  attr_reader :name_generator

  def initialize(pattern = 'LLDDD')
    @pattern = pattern.reverse.chars.map(&:to_sym)
    unless @pattern.all? { |sym| PATTERN_MAP.key?(sym) }
      fail ArgumentError, "Invalid pattern #{pattern}"
    end

    self.name_generator = :incrementing
  end

  def name
    @name ||= name_generator.name(@pattern).reverse
  end

  def reset
    @name = nil
  end

  def name_generator=(generator)
    unless NAME_GENERATORS.include? generator
      fail ArgumentError, "Invalid name generator #{generator}"
    end

    @name_generator =
      self.class.const_get :"#{generator.to_s.capitalize}NameGenerator"
  end

  def maximum_names
    @pattern.map { |sym| PATTERN_MAP[sym].length }.reduce(:*)
  end

  module IncrementingNameGenerator
    @incrementing_id = -1

    class << self
      attr_accessor :incrementing_id

      def name(pattern)
        remainder = increment_id

        pattern.map do |sym|
          chars = PATTERN_MAP[sym]
          remainder, modulus = remainder.divmod chars.length
          chars[modulus]
        end.join
      end

      private

      def increment_id
        self.incrementing_id += 1
      end
    end # class << self
  end # module IncrementingNameGenerator

  module RandomNameGenerator
    class << self
      def name(pattern)
        pattern.map { |sym| PATTERN_MAP[sym].sample }.join
      end
    end # class << self
  end # module RandomNameGenerator
end # class Robot
