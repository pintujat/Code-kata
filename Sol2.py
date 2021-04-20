class Sol2:
    def findTheWord(self,words: [str]) -> [str]:
        # File opening and reading words
        with open("wordlist.txt", 'r') as file_in:
            fileWords = file_in.read().splitlines()
        word_dict = {word:0 for word in fileWords}
        result = []
        for word in fileWords:
            del word_dict[word]
            # Iterating and checking length of the word 
            if self.dfs(word, word_dict):
                if len(word) == 6 :
                    result.append(word)
            word_dict[word] = 0
        # Writing the output file with result list data
        with open('output_py.txt', 'w') as file_out:
            file_out.write('\n'.join(result))
        return result
    # depth first approach for iterating over words
    def dfs(self, word, word_dict):
        i = 0
        j = 1
        while j <= len(word):
            if word[i:j] in word_dict:
                
                if word[j:] == "" or self.dfs(word[j:], word_dict):
                    return True
            j += 1
        return False
res = Sol2()   
res.findTheWord([])
# print(res.findTheWord(["by","pass","bypass","carton","car","ton","den","dry","Dryden","cart","on","carton"]))