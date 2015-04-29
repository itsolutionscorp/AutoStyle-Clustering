class Bob
  def hey(query_string)
    query = Query.new(query_string)

    if query.silent?
      "Fine. Be that way!"
    elsif query.yelling?
      "Woah, chill out!"
    elsif query.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Query
  def initialize(string)
    @string = string
  end

  def silent?
    blank?
  end

  def yelling?
    uppercase_but_no_lowercase_letters?
  end

  def question?
    string.end_with?("?")
  end

  private

  def blank?
    string.strip.empty?
  end

  def uppercase_but_no_lowercase_letters?
    string =~ /[A-Z]/ && string !~ /[a-z]/
  end

  def string
    @string
  end
end
