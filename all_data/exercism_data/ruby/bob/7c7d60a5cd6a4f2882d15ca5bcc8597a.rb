class Bob
  def hey(s)
    [ -> s { s.empty?         && "Fine. Be that way!" },
      -> s { s == s.upcase    && "Woah, chill out!"   },
      -> s { s.end_with?("?") && "Sure."              },
      -> s {                     "Whatever."          }
    ].inject(nil) {|m,p| m || p[s.to_s] }
  end
end
