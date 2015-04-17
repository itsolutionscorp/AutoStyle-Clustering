class Bob
  def hey input
    perception = Perception.new input

    if    perception.nothing?  then 'Fine. Be that way!'
    elsif perception.yelling?  then 'Woah, chill out!'
    elsif perception.querying? then 'Sure.'
    elsif perception.telling?  then 'Whatever.'
    end
  end

  class Perception
    def initialize input
      @input = input
    end

    def nothing?
      ! input || input.empty?
    end

    def yelling?
      input.upcase == input
    end

    def querying?
      input.end_with?('?')
    end

    def telling?
      input.end_with?('.', '!')
    end

    private
    
    def input
      @input
    end
  end
end