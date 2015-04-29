class Bob

  def hey(whats_up)
    case whats_up

    when given_the_silent_treatment then he_says "Fine. Be that way!"
    when yelled_at                  then he_says "Woah, chill out!"
    when questioned                 then he_says "Sure."

    else he_says "Whatever."
    end
  end

  { # define the three specific things Bob responds to
    :questioned => /.*\?$/,
    :yelled_at => /^[^a-z]+$/,
    :given_the_silent_treatment => /^ *$/
  }.each do |interaction, pattern|
    define_method(interaction) { pattern }
  end

  private

  def he_says(something)
    something
  end

end
