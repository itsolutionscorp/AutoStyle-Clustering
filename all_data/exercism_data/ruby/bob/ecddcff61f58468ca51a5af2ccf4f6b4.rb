class Bob
  Responses = {
       silence: "Fine. Be that way!",
       yelling: "Woah, chill out!",
    a_question: "Sure.",
        drivel: "Whatever."
  }

  def hey(something)
    what_was_said = if something.nil? || something.empty?
                      :silence
                    elsif something.upcase == something
                      :yelling
                    elsif something.end_with? '?'
                      :a_question
                    else
                      :drivel
                    end

    respond_to what_was_said
  end

  def respond_to(stimulus)
    Responses.fetch(stimulus)
  end
end
