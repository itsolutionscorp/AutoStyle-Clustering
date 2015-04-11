class Bob

  def hey(input_to_bob)
    case String(input_to_bob)

    when %r(\A              \s*
            \z)x then       "Fine. Be that way!"

    when   /\A[^a-z0-9]*\z/,
         %r(\A[^a-z]*        !
            \z)x then       "Woah, chill out!"

    when %r(                \?
            \z)x then       "Sure."

    else                    "Whatever."
    end
  end

end
