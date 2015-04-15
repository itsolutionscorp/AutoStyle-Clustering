class Raindrops
  def self.convert(number)
    @number = number
    if prime_of_three? && prime_of_five? && prime_of_seven? then 'PlingPlangPlong'
    elsif prime_of_three? && prime_of_five?  then 'PlingPlang'
    elsif prime_of_three? && prime_of_seven? then 'PlingPlong'
    elsif prime_of_five?  && prime_of_seven? then 'PlangPlong'
    elsif prime_of_three? then 'Pling'
    elsif prime_of_five?  then 'Plang'
    elsif prime_of_seven? then 'Plong'
    else
      number.to_s
    end
  end

  def self.prime_of_three?
    @number % 3 == 0
  end

  def self.prime_of_five?
    @number % 5 == 0
  end

  def self.prime_of_seven?
    @number % 7 == 0
  end
end
