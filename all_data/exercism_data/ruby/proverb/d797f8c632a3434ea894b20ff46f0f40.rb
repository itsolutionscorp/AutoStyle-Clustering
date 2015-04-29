class Proverb
  
  def initialize(*parts)
    if parts.last.kind_of? Hash
      @qualifier = parts.pop.fetch(:qualifier)
    end
    @parts = parts 
  end

  def to_s
    add(all_verses) + ending
  end

  private

    def add(verses)
      verses.reduce(:+)
    end

    def all_verses
      @parts.each_cons(2).map do |current_part, next_part| 
        verse(current_part, next_part) 
      end
    end

    def verse(current_part, next_part)
      "For want of a #{current_part} the #{next_part} was lost.\n"
    end

    def ending
      if @qualifier 
        "And all for the want of a #{@qualifier} nail."
      else
        "And all for the want of a nail."
      end
    end

end
