class Robot

  def initialize
    @@names = []
    @rname = ""
  end

  def name
    if @rname == ""
      abc = ('A'..'Z').map { |letter| letter }
      two_letters = (0...2).map { abc[rand(abc.length)] }.join

      numbers = (0..9).map { |number| number.to_s }
      three_numbers = (0...3).map { numbers[rand(numbers.length)] }.join

      new_name_robot = two_letters + three_numbers
      @@names << new_name_robot
      @rname = new_name_robot

      if @@names.detect {|e| @@names.rindex(e) != @@names.index(e) }
        @@names = @@names - @@names.last
        name
      end

      return @rname
    end

    return @rname
  end

  def reset
    @@names = @@names - [@rname]
    @rname = ""
    name
  end
end
