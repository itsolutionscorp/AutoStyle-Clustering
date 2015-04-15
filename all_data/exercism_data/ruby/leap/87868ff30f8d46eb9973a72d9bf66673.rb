class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    if is_leap = divisible_by_4?
      if divisible_by_100? && !divisible_by_400?
        is_leap = false
      end
    end

    is_leap
  end

  private

  def method_missing(method, *args, &block)
    if method.to_s.start_with?('divisible_by_')
      number = get_number_from_method_name(method)
      year.modulo(number).zero?
    else
      super
    end
  end

  def get_number_from_method_name(method)
    method.to_s.split('_').last.chop.to_i
  end
end
