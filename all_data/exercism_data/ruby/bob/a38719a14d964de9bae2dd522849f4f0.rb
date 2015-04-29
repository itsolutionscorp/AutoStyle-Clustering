class Bob
  def hey(what)
    what = forget_that_noise(what)

    if saying_a_whole_lotta_nothing?(what)
      "Fine. Be that way!"
    elsif shouting_at_me?(what)
      "Woah, chill out!"
    elsif asking_me_something?(what) 
      "Sure."
    else 
      "Whatever."
    end
  end

  private
  
  def shouting_at_me?(what)
    words = what.just_gimme_letters_k_plz.split
    return false if words.empty?
    words.all_caps?
  end

  def saying_a_whole_lotta_nothing?(what)
    what.strip.empty?
  end

  def asking_me_something?(what)
    what =~ /\?$/
  end

  def forget_that_noise(what)
    return what unless what.include?("\n")
    what.split("\n").last
  end
end

class String
  def just_gimme_letters_k_plz
    gsub(/[\W\d]+/, " ")
  end
end

class Array
  def all_caps?
    all? { |v| v =~ /[A-Z]+/ }   
  end
end
