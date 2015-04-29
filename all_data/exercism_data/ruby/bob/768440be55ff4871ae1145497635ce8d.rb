class Bob
  def hey(statement)

    if silent(statement)
      "Fine. Be that way."
    elsif forceful(statement)
      "Woah, chill out!"
    elsif rhetorical(statement)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def forceful(st)
    st.upcase == st
  end

  def rhetorical(st)
    st.end_with?('?')
  end

  def silent(st)
    st !~ /\S/  || nil
  end
end
