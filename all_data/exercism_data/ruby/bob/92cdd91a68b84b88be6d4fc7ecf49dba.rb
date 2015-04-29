class Person

  private

  { # describe all the ways someone might talk to me
    :yelled_at                  => lambda { |s| s.strip.length > 0 && 
                                                s.upcase == s         },
    :questioned                 => lambda { |s| s.end_with?("?")      },
    :given_the_silent_treatment => lambda { |s| s.strip.empty?        }
  }.each do |kind_of_thing, check_method|
    define_method(kind_of_thing) do
      Class.new(Object) { define_method(:===, &check_method) }.new
    end
  end

  def he_says(something)
    something
  end

  alias :she_says :he_says

end

class Bob < Person

  def hey(whats_up)
    case whats_up

    when given_the_silent_treatment then he_says "Fine. Be that way!"
    when yelled_at                  then he_says "Woah, chill out!"
    when questioned                 then he_says "Sure."

    else he_says "Whatever."
    end
  end

end
