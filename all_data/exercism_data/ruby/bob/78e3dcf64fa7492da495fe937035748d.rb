class Bob
  def hey(s)
    [ -> s { s == ""          && "Fine. Be that way!" },
      -> s { s.upcase!.nil?   && "Woah, chill out!"   },
      -> s { s.end_with?("?") && "Sure."              },
      -> s {                     "Whatever."          }
    ].inject(nil) {|m,p| m || p[s.to_s] }
  end
end
