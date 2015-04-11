module Acronym
  module_function
  def abbreviate( phrase )
    phrase.
      scan( /[A-Z]+[a-z]*|[a-z]+/ ).
      map { |word| word[0] }.
      join.
      upcase
  end
end
