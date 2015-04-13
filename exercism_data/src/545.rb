def compute(string1, string2)
        diff = 0

        string1.split("").each_with_index do |ch, index|
                diff += 1 if ch != string2[index]
        end
        diff
    end