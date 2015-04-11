module Acronym
  module_function
  def abbreviate( phrase )
    phrase.
      gsub( /([a-z])([A-Z])/, '\1 \2' ).
      split( /\W+/ ).
      map { |word| word[0] }.
      join.
      upcase
  end
end
