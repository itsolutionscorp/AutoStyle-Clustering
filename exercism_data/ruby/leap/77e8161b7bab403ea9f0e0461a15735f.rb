class Year
  def self.leap?(year_number)
    divisible_by = ->(divisor) { year_number % divisor == 0 }

    case
      when divisible_by.(400)  then true
      when divisible_by.(100)  then false
      when divisible_by.(4)    then true
      else false
    end
  end
end
