class Bob
  def hey(query)
    query = Query.new(query)

    if query.not_saying_anything?
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
  def initialize(query)
    @query = query
  end

  def not_saying_anything?
    query_is_blank?
  end

  def yelling?
    query_has_uppercase_but_no_lowercase_letters?
  end

  def question?
    query.end_with?("?")
  end

  private

  def query_is_blank?
    query.strip == ""
  end

  def query_has_uppercase_but_no_lowercase_letters?
    query =~ /[A-Z]/ && query !~ /[a-z]/
  end

  def query
    @query
  end
end
