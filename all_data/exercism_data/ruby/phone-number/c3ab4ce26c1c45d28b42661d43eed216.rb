class PhoneNumber

  attr_reader :number

  def initialize number
    @number = refine_number(number)
  end

  def area_code
    @number[0,3]
  end

  def to_s
    "(#{area_code}) #{@number[3,3]}-#{@number[6,4]}"
  end

  private

    def refine_number number
      refined = number.gsub /\W+/,''

      return "0"*10 if refined[/[a-zA-Z]/] ||
                       ![10,11].include?(refined.length)

      if refined.length == 11
        refined.start_with?('1') ? refined.sub('1','') : "0"*10
      else
        refined
      end
    end
end
