Atbash = Object.new

def Atbash.encode(string)
  @reversed_chars ||= Hash[  [*'a'..'z'].zip([*'a'..'z'].reverse)  ]

  string.downcase
        .gsub(/./) { |char|
          next char                  if char =~ /[1-9]/
          next @reversed_chars[char] if char =~ /[a-z]/
          next ''
        }
        .scan(/.{1,5}/)
        .join(" ")
end
