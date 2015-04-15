class Bob
  def hey(query)
    # I'm fine with replacing "query" right away here with
    # something more useful than a raw string. As long as it
    # happens in the very beginning of the method so there is no
    # confusion as to what query is.
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
  # I tend to use https://github.com/barsoom/attr_extras which
  # would make the constructor and the private reader just
  # pattr_initialize :query.
  def initialize(query)
    @query = query
  end

  def not_saying_anything?
    query_is_blank?
  end

  def yelling?
    query_has_capital_letters_and_no_lowercase_letters?
  end

  def question?
    query.end_with?("?")
  end

  private

  def query_is_blank?
    query.strip == ""
  end

  def query_has_capital_letters_and_no_lowercase_letters?
    # This can probably be done with a single regex, but this
    # is very clear code and much simpler to write.
    query =~ /[A-Z]/ && query !~ /[a-z]/
  end

  # I like using bare words instead of referencing instance variables
  # directly because they are more flexible when refactoring.
  def query
    @query
  end
end
